package org.goldenglue.game;

import java.util.HashSet;
import java.util.Set;

public class GameState {
    private Set<Player> players = new HashSet<>();

    public void addPlayerIntoGame(Player player) {
        players.add(player);
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
