package DAO;

import java.util.ArrayList;

public interface iModel<E> {
    public String fromArrayToJson(ArrayList<E> bean);
    public String toArrayJson(ArrayList<E> bean);
    public String toSqlWhereString();
}

