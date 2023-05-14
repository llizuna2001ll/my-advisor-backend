package com.hgsplanet.userservice.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(collection = "business-types")
public class BusinessType {
    @Id
    private String typeId;
    @Indexed(unique = true)
    private String typeName;
    private String imgPath;
    private int businessCount;
}
