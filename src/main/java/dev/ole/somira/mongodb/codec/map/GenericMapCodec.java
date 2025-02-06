package dev.ole.somira.mongodb.codec.map;

import dev.ole.somira.MongoManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import org.bson.*;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecConfigurationException;
import org.bson.json.JsonReader;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log
public class GenericMapCodec<K, T> implements Codec<Map<K, T>> {

    MongoManager manager;
    @Getter
    Class<Map<K, T>> encoderClass;
    Codec<K> keyCodec;
    Codec<T> valueCodec;

    public GenericMapCodec(MongoManager manager, Class<Map<K, T>> encoderClass, Codec<K> keyCodec, Codec<T> valueCodec) {
        this.manager = manager;
        this.encoderClass = encoderClass;
        this.keyCodec = keyCodec;
        this.valueCodec = valueCodec;
    }

    @Override
    public void encode(BsonWriter writer, Map<K, T> map, EncoderContext encoderContext) {
        try (BsonDocumentWriter documentWriter = new BsonDocumentWriter(new BsonDocument())) {
            documentWriter.writeStartDocument();
            writer.writeStartDocument();

            for (Map.Entry<K, T> entry : map.entrySet()) {
                PropertyEditor editor = PropertyEditorManager.findEditor(keyCodec.getEncoderClass());
                if (editor != null) {
                    log.fine("Found PropertyEditor for class: " + keyCodec.getEncoderClass().getName());

                    editor.setValue(entry.getKey());
                    writer.writeName(editor.getAsText());
                } else {
                    String documentId = UUID.randomUUID().toString();
                    documentWriter.writeName(documentId);
                    keyCodec.encode(documentWriter, entry.getKey(), encoderContext);
                    String keyString;
                    BsonValue bsonValue = documentWriter.getDocument().asDocument().get(documentId);
                    if (UUID.class.isAssignableFrom(keyCodec.getEncoderClass())
                            && bsonValue.isBinary()
                            && !manager.getSettingsBuilder().isDisallowUUIDKeys()) {
                        ByteBuffer buffer = ByteBuffer.wrap(bsonValue.asBinary().getData());
                        keyString = new UUID(buffer.getLong(), buffer.getLong()).toString();
                    } else {
                        keyString = bsonValue.asString().getValue();
                    }
                    writer.writeName(keyString);
                }

                valueCodec.encode(writer, entry.getValue(), encoderContext);
            }
            documentWriter.writeEndDocument();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to encode map: " + map, e);
            throw new IllegalArgumentException(e);
        }
        writer.writeEndDocument();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<K, T> decode(BsonReader reader, DecoderContext context) {
        reader.readStartDocument();
        Map<K, T> map = getInstance();
        while (!BsonType.END_OF_DOCUMENT.equals(reader.readBsonType())) {
            K key;
            PropertyEditor editor = PropertyEditorManager.findEditor(keyCodec.getEncoderClass());
            String keyName = reader.readName();
            if (editor != null) {
                log.fine("Found PropertyEditor for class: " + keyCodec.getEncoderClass().getName());
                editor.setAsText(keyName);
                key = (K) editor.getValue();
            } else {
                JsonReader jsonReader = new JsonReader(String.format("\"key\": \"%s\"", keyName));
                BsonType bsonType = reader.getCurrentBsonType();
                boolean isUUID = UUID.class.isAssignableFrom(keyCodec.getEncoderClass());
                if (isUUID && bsonType == BsonType.STRING) {
                    key = (K) UUID.fromString(keyName);
                } else {
                    key = keyCodec.decode(jsonReader, context);
                }
            }

            T value = null;
            if (!BsonType.NULL.equals(reader.getCurrentBsonType())) {
                value = valueCodec.decode(reader, context);
            }
            map.put(key, value);
        }
        reader.readEndDocument();
        return map;
    }

    private Map<K, T> getInstance() {
        if (encoderClass.isInterface()) {
            return new HashMap<>();
        }
        try {
            return encoderClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new CodecConfigurationException(e.getMessage(), e);
        }
    }
}