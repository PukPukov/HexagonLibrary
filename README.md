# HexagonLibrary
## Описание
Универсальная библиотека на Java для создания гексагональных сеток и работы с ними. 
## Преимущества
- Осевая система координат
- Деление гексагона на примитивные компоненты для реализации любых задач
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
| HexagonalGrid | .getHexagon(Point point) | Возвращает гексагон, в котором находится данная точка |
| HexagonalGrid | .getBounds(Hexagon[] hexagons) | Возвращает массив сторон, являющимися сторонами геометрической фигуры, составленной из данных гексагонов |
| HexagonalGrid | .createRegion(Point[] geometry) | Возвращает регион, образованный данными точками |
| Hexagon | .getCenter() | Возвращает точку центра гексагона |
| Hexagon | .getSide(int index) | Возвращает объект стороны гексагона |
| Hexagon | .getSides() | Возвращает массив всех сторон гексагона |
| Hexagon | .getVertex(int index) | Возвращает объект вершины гексагона |
| Hexagon | .getVertexes() | Возвращает массив всех вершин гексагона |
| Hexagon | .getVertexPosition(int index) | Возвращает точку вершины гексагона |
| Hexagon | .getNeighbor(int index) | Возвращает соседа по индексу |
| Hexagon | .getNeighbor(HexagonalDirection hexagonalDirection) | Возвращает соседа по гексагональному направлению |
| Hexagon | .getNeighbors() | Возвращает упорядоченно (индекс соседа соответствует индексу стороны) всех соседей 1 порядка |
| Hexagon | .getNeighbors(int layers) | Возвращает неупорядоченно всех соседей указанного порядка, работает быстрее |
| Side | .getHexagonalDirection() | Возвращает гексагональное направление этой стороны |
| Side | .getConnected() | Возвращает присоединённый к этой стороне шестиугольник |
| Side | .getEnds() | Возвращает точки концов этой стороны |
| Side | .getEqual() | Возвращает равную этой стороне сторону у соседнего шестиугольника |
| Side | .getAbsolute() | Возвращает эту сторону в абсолютной системе координат сторон (не имеющей дубликатов) |
| Vertex | .getConnected() | Возвращает шестиугольники, присоединённые к этой вершине |
| Vertex | .getEquals() | Возвращает равные этой вершине вершины других шестиугольников |
| Vertex | .getAbsolute() | Возвращает эту вершину в абсолютной системе координат вершин |
| Vertex | .getPosition() | Возвращает точку позиции этой вершины |
| Vertex | .getPosition() | Возвращает точку позиции этой вершины |
## API нижнего уровня
| Объект | Метод | Описание|
|----:|:----:|:----------|
## Конструкторы
| Объект | Конструктор |
|----:|:----------|
| HexagonalGrid | new HexagonalGrid(Orientation orientation, Point origin, Point size, Morton64 mort) |
| Hexagon | new Hexagon(HexagonalGrid grid, long q, long r) |
| Side | new Side(Hexagon baseHexagon, int sideIndex) |
| Vertex | new Vertex(Hexagon hexagon, int vertexIndex) |
| Point | new Point(double x, double y) |
| Orientation | new Orientation(String name, double[] f, double[] b, double startAngle) |
| FractionalHexagon | new FractionalHexagon(double q, double r, HexagonalGrid grid) |
| Region | new Region(HexagonalGrid grid, Point[] geometry) |
| Morton64 | new Morton64(long dimensions, long bits) |
