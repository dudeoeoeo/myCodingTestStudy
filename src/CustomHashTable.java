public class CustomHashTable {
    public Slot[] hashTable;

    public CustomHashTable(Integer size) {
        this.hashTable = new Slot[size];
    }

    public class Slot {
        String key;
        String value;
        Slot(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public int hashFunc(String key) {
        return (int)(key.charAt(0)) % this.hashTable.length;
    }

    // 해쉬 테이블 저장공간 안에서 충돌 문제를 해결하는 기법
    // 충돌이 일어나면, 해당 hash address 의 다음 address 부터 맨 처음 나오는 빈공간에 저장하는 기법
    // 저장공간 활용도를 높이기 위한 기법
    public boolean saveData(String key, String value) {
        Integer address = this.hashFunc(key);
        if (this.hashTable[address] != null) {
            if (this.hashTable[address].key == key) {
                this.hashTable[address].value = value;
                return true;
            } else {
                Integer currAddress = address + 1;
                while (this.hashTable[currAddress] != null) {
                    if (this.hashTable[currAddress].key == key) {
                        this.hashTable[currAddress].value = value;
                        return true;
                    } else {
                        currAddress++;
                        if (currAddress >= this.hashTable.length) {
                            return false;
                        }
                    }
                }
                this.hashTable[currAddress] = new Slot(key, value);
                return true;
            }
        } else {
            this.hashTable[address] = new Slot(key, value);
        }
        return true;
    }

    public String getData(String key) {
        Integer address = this.hashFunc(key);
        if (this.hashTable[address] != null) {
            if (this.hashTable[address] != null) {
                return this.hashTable[address].value;
            } else {
                Integer currAddress = address;
                while (this.hashTable[currAddress].key == key) {
                    if (this.hashTable[currAddress].key == key) {
                        return this.hashTable[currAddress].value;
                    } else {
                        currAddress++;
                        if (currAddress >= this.hashTable.length) {
                            return null;
                        }
                    }
                }
                return null;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        CustomHashTable hash = new CustomHashTable(5);
        hash.saveData("JAVA", "Programming");
        hash.saveData("Python", "AI");
        hash.saveData("JavaScript", "All");
        hash.saveData("PHP", "Server");
        hash.saveData("GO", "Fast");
        hash.saveData("ML", "Fast");
        hash.saveData("ML1", "Fast");
        hash.saveData("ML2", "Fast");
        hash.saveData("ML3", "Fast");
        hash.saveData("ML4", "Fast");
        hash.saveData("ML5", "Fast");
        hash.saveData("ML6", "Fast");

        System.out.println(hash.getData("PHP"));
    }
}
