package gui.panels;

public enum SortingType {
    /*
        case 0 -> src = Client.manager.keySort();
            case 1 -> src = Client.manager.nameSort();
            case 2 -> src = Client.manager.idSort();
            case 3 -> src = Client.manager.ageSort();
            case 4 -> src = Client.manager.coordinatesSort();
            case 5 -> src = Client.manager.dateSort();
            case 6 -> src = Client.manager.colorSort();
            case 7 -> src = Client.manager.typeSort();
            case 8 -> src = Client.manager.characterSort();
            case 9 -> src = Client.manager.caveSort();
            case 10 -> src = Client.manager.ownerSort();
        */
    KEY,
    NAME,
    ID,
    AGE,
    COORDINATES,
    CREATION_DATE,
    COLOR,
    TYPE,
    CHARACTER,
    CAVE,
    OWNER;

    public static int type(SortingType sortingType){
        switch (sortingType){
            case NAME -> {
                return 1;
            }
            case ID -> {
                return 2;
            }
            case AGE -> {
                return 3;
            }
            case COORDINATES -> {
                return 4;
            }
            case CREATION_DATE -> {
                return 5;
            }
            case COLOR -> {
                return 6;
            }
            case TYPE -> {
                return 7;
            }
            case CHARACTER -> {
                return 8;
            }
            case CAVE -> {
                return 9;
            }
            case OWNER -> {
                return 10;
            }
            default -> {
                return 0;
            }
        }
    }
}
