package DAO;

import java.util.ArrayList;

public interface iDao <E,I> {
    public int add(E bean);
    public int delete (I e);
    public int update(E bean);
    public ArrayList<E> FindAll(E bean);
}
