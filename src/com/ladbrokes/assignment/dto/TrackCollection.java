package com.ladbrokes.assignment.dto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TrackCollection {

    private static Logger logger = LogManager.getLogger(TrackCollection.class);

    private static List<Track> tracks = new ArrayList<Track>();

    private static class SingletonHolder{
        private static final TrackCollection instance = new TrackCollection();
    }

    public static TrackCollection getInstance(){
        return SingletonHolder.instance;
    }

    /**
     * delete a track by it's id
     * @param id
     */
    public void removeTrackById(Long id){
        synchronized (tracks ) {
            tracks.removeIf(track -> track.getId().equals(id));
            System.out.println("Successs removing track by id");
        }
    }

    /**
     * delete track by it's permalink
     * @param permaLink
     */
    public void removeTrackByPermaLink(String permaLink){
        synchronized (tracks ) {
            tracks.removeIf(track -> track.getPermaLinkUrl().equals(permaLink));
            System.out.println("Successs removing track by permalink url");
        }
    }

    /**
     * save a collection of track to the class
     * @param trackToSave
     */
    public void saveTracksInCollection(Collection<Track> trackToSave){
        System.out.println("Saving tracks");
        for(Track track : trackToSave){
            getTracks().add(track);
        }

        System.out.println("getTracks().size(): " + getTracks().size());

    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        synchronized (tracks){
            TrackCollection.tracks = tracks;
        }
    }

    public void printResultsToLog(){
        for(Track track : getTracks()){
            logger.info(track);
        }
    }
}
