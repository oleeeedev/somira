package dev.ole.somira.repository.methods.fields;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateBatch {

    public static UpdateBatch of(Collection<FieldUpdate> fieldUpdateList) {
        return new UpdateBatch(new ArrayList<>()).addAll(fieldUpdateList);
    }

    public static UpdateBatch of(FieldUpdate... fieldUpdateArray) {
        return new UpdateBatch(new ArrayList<>()).addAll(fieldUpdateArray);
    }

    public static UpdateBatch of(FieldUpdate fieldUpdate) {
        return new UpdateBatch(new ArrayList<>()).add(fieldUpdate);
    }

    List<FieldUpdate> updateList;

    public UpdateBatch add(FieldUpdate fieldUpdate) {
        updateList.add(fieldUpdate);
        return this;
    }

    public UpdateBatch addAll(FieldUpdate... fieldUpdateArray) {
        return addAll(Arrays.asList(fieldUpdateArray));
    }

    public UpdateBatch addAll(Collection<FieldUpdate> fieldUpdateList) {
        updateList.addAll(fieldUpdateList);
        return this;
    }
}