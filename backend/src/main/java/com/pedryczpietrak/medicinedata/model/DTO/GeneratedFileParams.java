package com.pedryczpietrak.medicinedata.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GeneratedFileParams {
    String sortBy;
    boolean isAscending;
    int elementsNum;
    List<String> nullFields;

    public GeneratedFileParams(String sortBy, boolean isAscending, int elementsNum, List<String> nullFields) {
        this.sortBy = sortBy;
        this.isAscending = isAscending;
        this.elementsNum = elementsNum;
        this.nullFields = nullFields;
    }

    public GeneratedFileParams() {
    }
}
