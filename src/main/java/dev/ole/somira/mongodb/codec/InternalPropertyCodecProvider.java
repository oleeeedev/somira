package dev.ole.somira.mongodb.codec;

import dev.ole.somira.MongoManager;
import dev.ole.somira.mongodb.codec.lang.ClassCodec;
import dev.ole.somira.mongodb.codec.map.GenericMapCodec;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import org.bson.codecs.Codec;
import org.bson.codecs.pojo.PropertyCodecProvider;
import org.bson.codecs.pojo.PropertyCodecRegistry;
import org.bson.codecs.pojo.TypeWithTypeParameters;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Log
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InternalPropertyCodecProvider implements PropertyCodecProvider {

    MongoManager manager;
    Map<Class<?>, Codec<?>> customCodecRegistry;

    public InternalPropertyCodecProvider(MongoManager manager) {
        this.manager = manager;
        this.customCodecRegistry = new LinkedHashMap<>();
    }

    public <T> void registerCodec(Class<T> typeClass, Codec<T> typeCodec) {
        this.customCodecRegistry.put(typeClass, typeCodec);
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public <T> Codec<T> get(TypeWithTypeParameters<T> type, PropertyCodecRegistry registry) {
        Class<T> typeClass = type.getType();
        List<? extends TypeWithTypeParameters<?>> typeParameters = type.getTypeParameters();

        if (Map.class.isAssignableFrom(typeClass) && typeParameters.size() == 2) {
            return new GenericMapCodec<>(manager, typeClass, registry.get(typeParameters.get(0)),
                    registry.get(typeParameters.get(1)));
        }
        if (Class.class.isAssignableFrom(typeClass)) {
            return (Codec<T>) new ClassCodec();
        }
        Codec<?> codec = customCodecRegistry.get(typeClass);
        if (codec != null) {
            return (Codec<T>) codec;
        }
        return null;
    }

}