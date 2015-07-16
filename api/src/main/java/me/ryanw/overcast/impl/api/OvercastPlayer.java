package me.ryanw.overcast.impl.api;

import com.google.common.base.Optional;

import java.util.List;

//TODO: javadocs
public interface OvercastPlayer {

    String getUsername();

    Optional<String> getFormerUsername();

    List<OvercastPlayer> getFriends();

    int getGlobalKills();

    int getGlobalDeaths();

    int getGlobalKdRatio();

    int getGlobalKkRatio();

    int getGlobalDaysPlayed();

    int getProjectAresKills();

    int getProjectAresDeaths();

    int getProjectAresKd();

    int getProjectAresKk();

    int getProjectAresDaysPlayed();

    int getProjectAresDayObserved();

    int getBlitzKills();

    int getBlitzDeaths();

    int getBlitzKd();

    int getBlitzKk();

    int getBlitzDaysPlayed();

    int getBlitzDaysObserved();

    int getGhostSquadronKills();

    int getGhostSquadronDeaths();

    int getGhostSquadronKd();

    int getGhostSquadronKk();

    int getGhostSquadronDaysPlayed();

    int getGhostSquadronDaysObserved();

    int getServerJoins();

    int getRaindrops();

    int getForumPosts();

    int getForumTopics();

    int getMonumentsDestroyed();

    int getWoolsPlaced();

    int getCoresLeaked();

    Optional<String> getGender();

    Optional<String> getLocation();

    Optional<String> getOccupation();

    Optional<String> getInterests();

    Optional<String> getBiography();

    Optional<String> getSkypeHandle();

    Optional<String> getSteamHandle();

    Optional<String> getTwitterHandle();

    Optional<String> getTwitchHandle();

    Optional<String> getFacebookHandle();

    Optional<String> getGithubHandle();

    Optional<String> getRedditHandle();
}
