# Не секрет, что некоторые программисты очень любят путешествовать.
# Хорошо всем известный программист Петя тоже очень любит путешествовать,
# посещать музеи и осматривать достопримечательности других городов.
# Для перемещений между из города в город он предпочитает использовать машину.
# При этом он заправляется только на станциях в городах, но не на станциях по пути.
# Поэтому он очень аккуратно выбирает маршруты, чтобы машина не заглохла в дороге.
# А ещё Петя — очень важный член команды, поэтому он не может себе позволить путешествовать слишком долго.

# Он решил написать программу, которая поможет ему с выбором очередного путешествия.
# Но так как сейчас у него слишком много других задач, он попросил вас помочь ему.

# Расстояние между двумя городами считается как сумма модулей разности по каждой из координат.
# Дороги есть между всеми парами городов.

# В первой строке входных данных записано количество городов n (2 ≤ n ≤ 1000).
# В следующих n строках даны 2 целых числа: координаты каждого города, не превосходящие по модулю 1 миллиарда.
# Все города пронумерованы числами от 1 до n в порядке записи во входных данных.
# В следующей строке записано целое положительное число k, не превосходящее 2 миллиардов, —
# максимальное расстояние между городами, которое Петя может преодолеть без дозаправки машины.
# В последней строке записаны 2 различных числа — номер города, откуда едет Петя, и номер города, куда он едет.

# Если существуют пути, удовлетворяющие описанным выше условиям, то выведите минимальное количество дорог,
# которое нужно проехать, чтобы попасть из начальной точки маршрута в конечную.
# Если пути не существует, выведите -1.

def find_lowest_cost_node(costs, processed):
    lowest_cost = float('inf')
    lowest_cost_node = None
    for node in costs:
        cost = costs[node]
        if cost < lowest_cost and node not in processed:
            lowest_cost = cost
            lowest_cost_node = node
    return lowest_cost_node


def modified_dijkstra(graph, costs, parents, max_cost_between_two_nodes):
    processed = []
    node = find_lowest_cost_node(costs, processed)
    while node is not None:
        cost = costs[node]
        neighbors = graph[node]
        for n in neighbors.keys():
            cost_between_node_and_neighbor = neighbors[n]
            if cost <= max_cost_between_two_nodes and cost_between_node_and_neighbor <= max_cost_between_two_nodes:
                new_cost = cost + cost_between_node_and_neighbor
                if costs[n] > new_cost:
                    costs[n] = new_cost
                    parents[n] = node
        processed.append(node)
        node = find_lowest_cost_node(costs, processed)
    return parents


def get_shortest_path(parents, start, end):
    path = [end]
    while end != start:
        if end == None:
            return []
        end = parents[end]
        path.append(end)
    path.reverse()
    return path


def get_shortest_path_length(nodes_coords, max_cost_between_two_nodes, start_node, end_node):
    graph = {}
    costs = {}
    parents = {}
    n = len(nodes_coords)
    for i in range(1, n + 1):
        graph[i] = {}
        for j in range(1, n + 1):
            if i == j:
                continue
            graph[i][j] = abs(nodes_coords[i][0] - nodes_coords[j][0]) + \
                abs(nodes_coords[i][1] - nodes_coords[j][1])
        costs[i] = 0 if i == start_node else float('inf')
        parents[i] = None
    parents = modified_dijkstra(
        graph, costs, parents, max_cost_between_two_nodes)
    shortest_path = get_shortest_path(parents, start_node, end_node)
    return len(shortest_path) - 1


def main():
    n = int(input())
    nodes_coords = {}
    for i in range(1, n + 1):
        node_coords = input().split()
        nodes_coords[i] = (int(node_coords[0]), int(node_coords[1]))
    max_cost_between_two_nodes = int(input())
    start_and_end_nodes = input().split()
    start_node = int(start_and_end_nodes[0])
    end_node = int(start_and_end_nodes[1])
    shortest_path_length = get_shortest_path_length(nodes_coords,
                                                    max_cost_between_two_nodes,
                                                    start_node,
                                                    end_node)
    print(shortest_path_length)


if __name__ == '__main__':
    main()
