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
@Document(collection = "cities")
public class City implements Comparable<City> {
    @Id
    private String cityId;
    @Indexed(unique = true)
    private String name;
    private String cityDescription;
    private String imgPath;
    private int businessCount;

    @Override
    public int compareTo(City otherCity) {
        return Integer.compare(otherCity.businessCount, this.businessCount);
    }
}
