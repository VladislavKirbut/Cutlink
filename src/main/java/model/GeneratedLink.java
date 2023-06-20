package model;

import lombok.Value;

@Value
public class GeneratedLink {
    long generatedLinkId;
    String generatedLinkUrl;
    long userLinkId;
}
