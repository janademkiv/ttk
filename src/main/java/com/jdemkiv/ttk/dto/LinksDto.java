package com.jdemkiv.ttk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LinksDto {
    @JsonProperty("mission_patch")
    String missionPatch;
    @JsonProperty("mission_patch_small")
    String missionPatchSmall;
    @JsonProperty("reddit_campaign")
    String redditCampaign;
    @JsonProperty("reddit_launch")
    String redditLaunch;
    @JsonProperty("reddit_recovery")
    String redditRecovery;
    @JsonProperty("reddit_media")
    String reditMedia;
    @JsonProperty("presskit")
    String presskit;
    @JsonProperty("article_link")
    String articleLink;
    @JsonProperty("wikipedia")
    String wikipedia;
    @JsonProperty("video_link")
    String videoLink;
    @JsonProperty("youtube_id")
    String youtubeId;
    @JsonProperty("flickr_images")
    List<String> flickrImages;
}
