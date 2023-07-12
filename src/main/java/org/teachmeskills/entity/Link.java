package org.teachmeskills.entity;

import lombok.*;
import java.net.URI;

@Value
public class Link {
    long id;
    URI longUrl;
    URI shortUrl;
}
