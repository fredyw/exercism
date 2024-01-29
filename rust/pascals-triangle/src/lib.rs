pub struct PascalsTriangle {
    row_count: u32,
}

impl PascalsTriangle {
    pub fn new(row_count: u32) -> Self {
        PascalsTriangle { row_count }
    }

    pub fn rows(&self) -> Vec<Vec<u32>> {
        let mut rows = vec![];
        for i in 0..self.row_count {
            if i == 0 {
                rows.push(vec![1]);
            } else {
                let prev_row = rows.get(i as usize - 1).unwrap();
                let mut new_row = vec![1];
                for i in 0..prev_row.len() - 1 {
                    new_row.push(prev_row[i] + prev_row[i + 1]);
                }
                new_row.push(1);
                rows.push(new_row);
            }
        }
        rows
    }
}
