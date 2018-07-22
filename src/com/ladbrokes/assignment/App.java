package com.ladbrokes.assignment;

import com.ladbrokes.assignment.actions.FetchTracks;
import com.ladbrokes.assignment.dao.TrackDao;
import com.ladbrokes.assignment.dto.Track;
import com.ladbrokes.assignment.dto.TrackCollection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Comparator;
import java.util.Set;

public class App {

    private static Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args){
        FetchTracks f = new FetchTracks();

        try {
            f.perform("aliceinchains");
            f.perform("metallica");
            f.perform("alterbridge");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Track sampleTrack = TrackCollection.getInstance().getTracks().get(0);
        Track sampleTrack2 = TrackCollection.getInstance().getTracks().get(3);

        TrackCollection.getInstance().printResultsToLog();

        TrackCollection.getInstance().getTracks().sort(new Comparator<Track>() {
            @Override
            public int compare(Track t1, Track t2) {
                return t1.getTitle().compareTo(t2.getTitle());
            }
        });
        TrackCollection.getInstance().printResultsToLog();

        System.out.println(sampleTrack);
        System.out.println(sampleTrack2);

        TrackCollection.getInstance().removeTrackById(sampleTrack.getId());
        TrackCollection.getInstance().removeTrackByPermaLink(sampleTrack.getPermaLinkUrl());



    }
}
