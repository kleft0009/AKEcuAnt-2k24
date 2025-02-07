package AKBLC;

import AKDAC.AKIDAO;
import java.util.List;
import java.util.function.Supplier;

public class AKBLFactory<T> {

    private final AKIDAO<T> oDAO;

    public AKBLFactory(Supplier<AKIDAO<T>> supplier) {
        this.oDAO = supplier.get();
    }

    public List<T> getAll() throws Exception {
        return oDAO.readAll();
    }

    public T getBy(Integer id) throws Exception {
        return oDAO.readBy(id);
    }

    public boolean add(T oT) throws Exception {
        return oDAO.create(oT);
    }

    public boolean upd(T oT) throws Exception {
        return oDAO.update(oT);
    }

    public boolean del(Integer id) throws Exception {
        return oDAO.delete(id);
    }
}
