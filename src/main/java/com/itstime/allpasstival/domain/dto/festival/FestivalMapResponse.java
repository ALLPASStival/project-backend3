package com.itstime.allpasstival.domain.dto.festival;

import com.itstime.allpasstival.domain.entity.Festival;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FestivalMapResponse {
    public String title;
    public String img;
    public String place;
    public String detailPlace;
    public String link;
    public String latitude;
    public String longitude;

    public static FestivalMapResponse of(Festival festival) {
        String fullPlace = festival.getStreetAddr();
        String place="";
        String detailPlace="";
        System.out.println(fullPlace);
        if(!fullPlace.isEmpty()){
            String[] split = fullPlace.split(" ");
            place = split[0]+" "+ split[1];
            for (int i = 2; i < split.length; i++) {
                detailPlace+=split[i]+" ";
            }
        }
        return FestivalMapResponse.builder()
                .title(festival.getFestivalName())
                .img(festival.getEtc())
                .place(place)
                .detailPlace(detailPlace)
                .link(festival.getHomepAddr())
                .latitude(festival.getLatitude())
                .longitude(festival.getLongitude())
                .build();
    }
}
