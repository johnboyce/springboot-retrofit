package com.comcast.oktest.cache;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CacheConfig {
    private String name;
    private Integer maximumSize;
    private Integer expireAfterWrite;
}
