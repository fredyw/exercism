pub fn find_saddle_points(input: &[Vec<u64>]) -> Vec<(usize, usize)> {
    let num_rows = input.len();
    let num_cols = if num_rows > 0 { input[0].len() } else { 0 };
    let max_rows: Vec<u64> = (0..num_rows)
        .filter_map(|row| (0..num_cols).map(|col| input[row][col]).max())
        .collect();
    let min_cols: Vec<u64> = (0..num_cols)
        .filter_map(|col| (0..num_rows).map(|row| input[row][col]).min())
        .collect();
    input
        .iter()
        .enumerate()
        .flat_map(|(row, cols)| {
            let max_rows = &max_rows;
            let min_cols = &min_cols;
            cols.iter().enumerate().filter_map(move |(col, num)| {
                if *num == max_rows[row] && *num == min_cols[col] {
                    Some((row, col))
                } else {
                    None
                }
            })
        })
        .collect::<Vec<(usize, usize)>>()
}
