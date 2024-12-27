package com.roadmap.imageprocessingservice.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageTransformationRequest {

    private Transformations transformations;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Transformations {
        private Resize resize;
        private Crop crop;
        private Integer rotate;
        private String format;
        private Filters filters;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class Resize {
            private Integer width;
            private Integer height;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class Crop {
            private Integer width;
            private Integer height;
            private Integer x;
            private Integer y;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder
        public static class Filters {
            private Boolean grayscale;
            private Boolean sepia;
        }
    }
}


