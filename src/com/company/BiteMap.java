package com.company;

import com.company.Models.Element;

import java.util.ArrayList;

import static com.company.Utils.prinLine;
import static com.company.Utils.print;

/**
 * Класс битовой карты.
 */
class BiteMap {

    private boolean memory[] = new boolean[2048];
    private ArrayList<Element> elements = new ArrayList<>();

    // |_____|_____|____|____|
    // |__|__|__|__|____|____|
    BiteMap() {
        initMemory();
    }

    /**
     * Инициализируем пустую память
     *
     * @return Массив с байтами
     */
    private void initMemory() {
        print("Инициализация памяти\n");
        memory[0] = true;
        memory[1] = true;
        outPutByteMap();
        prinLine();
    }

    void WriteInto(char[] data) { // (a, b, c)
        prinLine();
        int size = data.length * 2 % 4 > 0 ? data.length * 2 / 4 + 1 : data.length * 2 / 4;
        boolean can = false;
        byte _size = 0;
        for (int i = 1; i < 64; i++) {
            if (!memory[i]) {
                if (++_size == size) {
                    can = true;
                    print("Позиция для записи: " + (i - _size + 1) + "; Размер: " + size + ";\n");
                    writeToMap(i - _size + 1, size);
                    addToList(new Element((i - _size + 1), size));
                    break;
                }
            } else _size = 0;
        }
        if (!can) {
            print("Не хватает памяти\n");
        }
        outPutByteMap();
    }

    private void addToList(Element element) {
        elements.add(element);
    }

    private void writeToMap(int startPos, int size) {
        for (int i = startPos; i < startPos + size; i++) memory[i] = true;
    }

    private void outPutByteMap() {
        print("Битовая карта:\n");
        for (int i =0 ; i < 64; i++) {
            if (memory[i]) print("1");
            else print("0");
        }
        prinLine();
        printElementsSize();
    }
    private void printElementsSize() {
        print("Размер элементов: " + elements.size());
        prinLine();
    }

    void deleteElementFromMap(int position) {
        prinLine();
        if (position > 1 && position < 64) {
            if (memory[position]) {
                for (Element element : elements) {
                    if (position >= element.getPos() && position < element.getPos() + element.getSize()) {
                        print("Сегмент занят, удаляем элемент и все сегменты принадлежащие этому элементу\n");
                        clearMemory(element.getPos(), element.getSize());
                        elements.remove(element);
                        outPutByteMap();
                        break;
                    }
                }
            } else print("Этот сегмент свободен\n");
        } else print("Такого сегмента нет\n");
    }

    private void clearMemory(int start, int size) {
       for (int i = start; i < start + size; i++) memory[i] = false;
    }

    void printStats() {
        prinLine();
        printFreeMemory();
        allocatedMemory();
        prinLine();
    }

    private void printFreeMemory() {
        int count = 0;
        for (int i = 0; i < 64; i++)
            if (!memory[i]) ++count;
        print("Свободно памяти: " + count * 4 + " байт\n");
    }

    private void allocatedMemory() {
        int count = 0;
        for (int i = 0; i < 64; i++)
            if (memory[i]) ++count;
        print("Занято памяти: " + count * 4 + " байт\n");
    }

}

