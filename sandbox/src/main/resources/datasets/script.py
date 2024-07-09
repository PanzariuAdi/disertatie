import os

def remap_graph(input_file, output_file):
    # Step 1: Read the graph data from the text file
    with open(input_file, 'r') as file:
        lines = file.readlines()

    # Step 2: Create a mapping for the large vertex numbers to a smaller range
    vertex_map = {}
    current_index = 1

    transformed_lines = []

    for line in lines:
        source, destination = line.split()
        source = int(source)
        destination = int(destination)

        if source not in vertex_map:
            vertex_map[source] = current_index
            current_index += 1
        if destination not in vertex_map:
            vertex_map[destination] = current_index
            current_index += 1

        # Step 3: Transform the graph data using this mapping
        transformed_source = vertex_map[source]
        transformed_destination = vertex_map[destination]

        transformed_lines.append(f"{transformed_source} {transformed_destination}\n")

    # Step 4: Save the transformed data to a new file
    with open(output_file, 'w') as file:
        file.writelines(transformed_lines)

    print(f"Graph data successfully transformed and saved to {output_file}")

# Example usage:
input_files = ['directed1.edges', 'directed2.edges','directed3.edges','directed4.edges','directed5.edges']  # List of input files
output_files = ['xdirected1.edges', 'xdirected2.edges','xdirected3.edges','xdirected4.edges','xdirected5.edges']  # Corresponding output files

for input_file, output_file in zip(input_files, output_files):
    remap_graph(input_file, output_file)
