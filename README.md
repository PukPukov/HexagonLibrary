# HexagonLibrary

![](https://img.shields.io/github/issues/pukpukov/HexagonLibrary-Java?style=for-the-badge&logo=appveyor) ![](https://img.shields.io/tokei/lines/github/pukpukov/HexagonLibrary-Java?style=for-the-badge&logo=appveyor)

## Описание
Универсальная библиотека на Java для создания гексагональных сеток и работы с ними. 
## Преимущества

![](https://img.shields.io/codefactor/grade/github/ancap-kun/HexagonLibrary?style=for-the-badge&logo=appveyor)

- Осевая система координат
- Деление гексагона на компоненты для реализации любых задач
- Широчайшее API
- Набор базовых методов для работы с сетками шестиугольников (Перевод пиксельных координат в осевые, нахождение соседей шестиугольника, нахождение координат вершин шестиугольника...)
- Набор дополнительных методов (Создание регионов, работа с массивами гексагонов, кодирование гексагона...)
## Пример кода
```java
    // Создание гексагональной сетки. Ориентация, начало координат, размер гексагона, настройки Morton64 (в большинстве случаев менять не надо)
    HexagonalGrid grid = new HexagonalGrid(Orientation.FLAT, new Point(0, 0), new Point(20, 20), new Morton64(2, 32));
    // Получение гексагона по пиксельным координатам
    Hexagon hexagon = grid.getHexagon(new Point(50, 50));
```
## API верхнего уровня
| Объект | Метод | Описание|
|----:|:----:|:----------|
| HexagonalGrid | .hexagon(Point point) | Возвращает гексагон, в котором находится данная точка |
| HexagonalGrid | .bounds(Hexagon[] hexagons) | Возвращает массив сторон, являющимися сторонами геометрической фигуры, составленной из данных гексагонов |
| HexagonalGrid | .region(Figure geometry) | Возвращает регион, образованный данными точками |
| Hexagon | .center() | Возвращает точку центра гексагона |
| Hexagon | .side(int index) | Возвращает объект стороны гексагона |
| Hexagon | .sides() | Возвращает массив всех сторон гексагона |
| Hexagon | .vertex(int index) | Возвращает объект вершины гексагона |
| Hexagon | .vertexes() | Возвращает массив всех вершин гексагона |
| Hexagon | .vertexPosition(int index) | Возвращает точку вершины гексагона |
| Hexagon | .neighbor(int index) | Возвращает соседа по индексу |
| Hexagon | .neighbor(HexagonalDirection hexagonalDirection) | Возвращает соседа по гексагональному направлению |
| Hexagon | .neighbors() | Возвращает упорядоченно (индекс соседа соответствует индексу стороны) всех соседей 1 порядка |
| Hexagon | .neighbors(int layers) | Возвращает неупорядоченно всех соседей указанного порядка, работает быстрее |
| Side | .nexagonalDirection() | Возвращает гексагональное направление этой стороны |
| Side | .connected() | Возвращает присоединённый к этой стороне шестиугольник |
| Side | .ends() | Возвращает точки концов этой стороны |
| Side | .equal() | Возвращает равную этой стороне сторону у соседнего шестиугольника |
| Side | .absolute() | Возвращает эту сторону в абсолютной системе координат сторон (не имеющей дубликатов) |
| Vertex | .connected() | Возвращает шестиугольники, присоединённые к этой вершине |
| Vertex | .equivalent() | Возвращает равные этой вершине вершины других шестиугольников |
| Vertex | .absolute() | Возвращает эту вершину в абсолютной системе координат вершин |
| Vertex | .position() | Возвращает точку позиции этой вершины |

## Конструкторы
| Объект | Конструктор |
|----:|:----------|
| HexagonalGrid | new HexagonalGrid(Orientation orientation, Point origin, Point size) |
