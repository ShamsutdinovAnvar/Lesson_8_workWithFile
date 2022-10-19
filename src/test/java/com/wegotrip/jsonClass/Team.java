package com.wegotrip.jsonClass;

import java.util.List;

public class Team {
    public List<Developers> team;
        public static class Developers {
            public String first_name;
            public String last_name;
            public Data data;

                public static class Data {
                    public String position;
                    public String age;
                    public String gender;
                    public String level;
                    public Integer id;
                    public boolean working;
                }
        }
}