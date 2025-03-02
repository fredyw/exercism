pub struct Matrix {
    rows: Vec<Vec<u32>>,
    cols: Vec<Vec<u32>>,
}

impl Matrix {
    pub fn new(input: &str) -> Self {
        let rows: Vec<Vec<u32>> = input
            .lines()
            .map(|line| {
                line.split(" ")
                    .map(|n| n.parse::<u32>().unwrap())
                    .collect::<Vec<_>>()
            })
            .collect();
        let num_rows = rows.len();
        let num_cols = if num_rows > 0 { rows[0].len() } else { 0 };
        let cols: Vec<Vec<u32>> = (0..num_cols)
            .map(|col| (0..num_rows).map(|row| rows[row][col]).collect::<Vec<_>>())
            .collect();
        Self { rows, cols }
    }

    pub fn row(&self, row_no: usize) -> Option<Vec<u32>> {
        Some(self.rows.get(row_no - 1).cloned()?)
    }

    pub fn column(&self, col_no: usize) -> Option<Vec<u32>> {
        Some(self.cols.get(col_no - 1).cloned()?)
    }
}
