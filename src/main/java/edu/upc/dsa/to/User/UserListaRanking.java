package edu.upc.dsa.to.User;

import java.util.List;

public class UserListaRanking {

    private List<UserRanking> rankings;

    public UserListaRanking(List<UserRanking> rankings) {
        this.rankings = rankings;
    }

    public UserListaRanking() {
    }

    public List<UserRanking> getRankings() {
        return rankings;
    }

    public void setRankings(List<UserRanking> rankings) {
        this.rankings = rankings;
    }
}
