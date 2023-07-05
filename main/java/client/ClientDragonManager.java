package client;

import comm.Unit;
import comm.clientReqs.InsertReq;
import comm.clientReqs.RemoveKeyReq;
import dragon.Dragon;


import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ClientDragonManager {
    private ConcurrentHashMap<Integer, Dragon> dragons;

    public ClientDragonManager() {
        dragons = new ConcurrentHashMap<>();
    }

    public synchronized void add(Integer k, Dragon d) {
        InsertReq insertReq = new InsertReq();
        insertReq.setArgLine(new String[]{k.toString()});
        dragons.put(k, d);
    }

    public synchronized void del(Integer k) {
        dragons.remove(k);
        RemoveKeyReq req = new RemoveKeyReq();
        req.setUser(Client.user);
        req.setArgLine(new String[]{k.toString()});
        if (req.create()) Client.terminal.mkCommand(req);
    }

    public synchronized void upd(ConcurrentHashMap<Integer, Dragon> NEW) {
        dragons = NEW;
    }

    public synchronized int size() {
        return dragons.size();
    }

    public synchronized void delById(int ID) {
        for (Integer d : dragons.keySet()) {
            if (dragons.get(d).getId() == ID) {
                dragons.remove(d);
                RemoveKeyReq req = new RemoveKeyReq();
                req.setUser(Client.user);
                req.setArgLine(new String[]{d.toString()});
                if (req.create()) Client.terminal.mkCommand(req);
                return;
            }
        }
    }

    public synchronized int getKeyById(int ID) {
        for (Integer k : dragons.keySet()) {
            if (dragons.get(k).getId() == ID) {
                return k;
            }
        }
        return 0;
    }

    public Integer[] getUserKeys(){
        List<Integer> ret = new LinkedList<>();
        for (Integer f : dragons.keySet()){
            if (dragons.get(f).getOwner().equals(Client.user.getLogin())) ret.add(f);
        }
        return (Integer[]) ret.toArray();
    }

    public synchronized ConcurrentHashMap<Integer, Dragon> getDragons() {
        return dragons;
    }
    public synchronized List<Unit> normal() {
        List<Unit> ret = new LinkedList<>();
        dragons.values().forEach(dragon -> {
            ret.add(new Unit(getKeyById(dragon.getId()), dragon));
        });
        return ret;
    }

    public synchronized List<Unit> keySort() {
        List<Unit> ret = new LinkedList<>();
        dragons.keySet().stream().sorted(Comparator.comparing(Integer::intValue)).forEach(key -> {
            ret.add(new Unit(key, dragons.get(key)));
        });
        return ret;
    }

    public synchronized List<Unit> nameSort() {
        List<Unit> ret = new LinkedList<>();
        dragons.values().stream().sorted(Comparator.comparing(Dragon::getName)).forEach(dragon -> {
            ret.add(new Unit(getKeyById(dragon.getId()), dragon));
        });
        return ret;
    }

    public synchronized List<Unit> idSort() {
        List<Unit> ret = new LinkedList<>();
        dragons.values().stream().sorted(Comparator.comparing(Dragon::getId)).forEach(dragon -> {
            ret.add(new Unit(getKeyById(dragon.getId()), dragon));
        });
        return ret;
    }

    public synchronized List<Unit> ageSort() {
        List<Unit> ret = new LinkedList<>();
        dragons.values().stream().sorted(Comparator.comparing(Dragon::getAge)).forEach(dragon -> {
            ret.add(new Unit(getKeyById(dragon.getId()), dragon));
        });
        return ret;
    }

    public synchronized List<Unit> coordinatesSort() {
        List<Unit> ret = new LinkedList<>();
        dragons.values().stream().sorted(Comparator.comparing(Dragon::getCoordinates)).forEach(dragon -> {
            ret.add(new Unit(getKeyById(dragon.getId()), dragon));
        });
        return ret;
    }

    public synchronized List<Unit> dateSort() {
        List<Unit> ret = new LinkedList<>();
        dragons.values().stream().sorted(Comparator.comparing(Dragon::getCreationDate)).forEach(dragon -> {
            ret.add(new Unit(getKeyById(dragon.getId()), dragon));
        });
        return ret;
    }

    public synchronized List<Unit> colorSort() {
        List<Unit> ret = new LinkedList<>();
        dragons.values().stream().sorted(Comparator.comparing(Dragon::getColor)).forEach(dragon -> {
            ret.add(new Unit(getKeyById(dragon.getId()), dragon));
        });
        return ret;
    }

    public synchronized List<Unit> typeSort() {
        List<Unit> ret = new LinkedList<>();
        dragons.values().stream().sorted(Comparator.comparing(Dragon::getType)).forEach(dragon -> {
            ret.add(new Unit(getKeyById(dragon.getId()), dragon));
        });
        return ret;
    }

    public synchronized List<Unit> characterSort() {
        List<Unit> ret = new LinkedList<>();
        dragons.values().stream().sorted(Comparator.comparing(Dragon::getCharacter)).forEach(dragon -> {
            ret.add(new Unit(getKeyById(dragon.getId()), dragon));
        });
        return ret;
    }

    public synchronized List<Unit> caveSort() {
        List<Unit> ret = new LinkedList<>();
        dragons.values().stream().sorted(Comparator.comparing(Dragon::getCave)).forEach(dragon -> {
            ret.add(new Unit(getKeyById(dragon.getId()), dragon));
        });
        return ret;
    }

    public synchronized List<Unit> ownerSort() {
        List<Unit> ret = new LinkedList<>();
        dragons.values().stream().sorted(Comparator.comparing(Dragon::getOwner)).forEach(dragon -> {
            ret.add(new Unit(getKeyById(dragon.getId()), dragon));
        });
        return ret;
    }
}
