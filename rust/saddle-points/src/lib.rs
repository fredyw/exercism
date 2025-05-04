pub fn find_saddle_points(input: &[Vec<u64>]) -> Vec<(usize, usize)> {
    let mut rows: Vec<u64> = vec![];
    let mut cols: Vec<u64> = vec![];
    let num_rows = input.len();
    let num_cols = if num_rows > 0 { input[0].len() } else { 0 };
    for row in 0..num_rows {
        let mut max = 0;
        for col in 0..num_cols {
            max = max.max(input[row][col]);
        }
        rows.push(max);
    }

    for col in 0..num_cols {
        let mut min = u64::MAX;
        for row in 0..num_rows {
            min = min.min(input[row][col]);
        }
        cols.push(min);
    }
    let mut answer: Vec<(usize, usize)> = vec![];
    for row in 0..num_rows {
        for col in 0..num_cols {
            if input[row][col] == rows[row] && input[row][col] == cols[col] {
                answer.push((row, col));
            }
        }
    }
    answer
}
