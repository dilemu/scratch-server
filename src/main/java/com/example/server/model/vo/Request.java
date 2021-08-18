package com.example.server.model.vo;

public class Request {

    private String terminal_id;

    private String query;

    public String getTerminal_id() {
        return terminal_id;
    }

    public void setTerminal_id(String terminal_id) {
        this.terminal_id = terminal_id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "Request{" +
                "terminal_id='" + terminal_id + '\'' +
                ", query='" + query + '\'' +
                '}';
    }
}
