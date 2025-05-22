package model;

import java.util.ArrayList;

public interface IModel<E> {
    public String fromArrayToJson(ArrayList<E> bean);
    public String toArrayJson(ArrayList<E> bean);
    public String toSqlWhereString();
}

