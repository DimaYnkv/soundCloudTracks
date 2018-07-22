package com.ladbrokes.assignment.actions;

import com.ladbrokes.assignment.dao.TrackDao;
import com.ladbrokes.assignment.dto.Track;
import com.ladbrokes.assignment.dto.TrackCollection;

import java.io.IOException;
import java.util.Set;

public class FetchTracks {
    TrackDao dao = new TrackDao();

    public void perform(final String bandName) throws InterruptedException {

        class TrackFetchRunnable implements Runnable {

            @Override
            public void run() {
                try {
                    Set<Track> trackSet = dao.retrieveTracksByBandName(bandName);
                    TrackCollection.getInstance().saveTracksInCollection(trackSet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Thread thread = new Thread(new TrackFetchRunnable());
        thread.start();
        thread.join();
    }

}
