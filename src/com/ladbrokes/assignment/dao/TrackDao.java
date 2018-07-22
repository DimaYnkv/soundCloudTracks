package com.ladbrokes.assignment.dao;

import com.ladbrokes.assignment.App;
import com.ladbrokes.assignment.common.IAppConsts;
import com.ladbrokes.assignment.dto.Track;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

public class TrackDao implements IAppConsts {
    private static Logger logger = LogManager.getLogger(TrackDao.class);

    private int recordsMaxThreshold = 10;

    public Set<Track> retrieveTracksByBandName(String bandName) throws IOException {

        Set<Track> trackSet = new HashSet<Track>();

        String inputLine;
        StringBuffer response = new StringBuffer();

        String bandNameQuery = String.format("q=%s", URLEncoder.encode(bandName, CHARSET_UTF_8));
        String clientIdQuery = String.format("client_id=%s", URLEncoder.encode(SOUNDCLOUD_CLIENT_ID,
                CHARSET_UTF_8));

        URL url = new URL(SOUNDCLOUD_BASE_URL
                + "?" + bandNameQuery
                + "&" + clientIdQuery);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        int responseCode = conn.getResponseCode();

        if (responseCode != 200) {
            logger.error("Failed opening connection to soundcloud. response code: " + responseCode);
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        } else {
            logger.info("TrackDao - search-query for bandName: " + bandName);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONArray arr = new JSONArray(response.toString());
            for (int i = 0; i < recordsMaxThreshold; i++) {
                Track track = new Track();
                Long id = arr.getJSONObject(i).getLong("id");
                String title = arr.getJSONObject(i).getString("title");
                String permalinkUrl = arr.getJSONObject(i).getString("permalink_url");
                Long user = arr.getJSONObject(i).getLong("user_id");

                track.setId(id);
                track.setPermaLinkUrl(permalinkUrl);
                track.setTitle(title);
                track.setUser(user);

                trackSet.add(track);
            }
        }

        return trackSet;
    }

    public int getRecordsMaxThreshold() {
        return recordsMaxThreshold;
    }

    public void setRecordsMaxThreshold(int recordsMaxThreshold) {
        this.recordsMaxThreshold = recordsMaxThreshold;
    }
}
