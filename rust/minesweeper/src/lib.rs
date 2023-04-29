static OFFSETS: &[(i32, i32)] = &[
    (-1, 0),
    (-1, 1),
    (0, 1),
    (1, 1),
    (1, 0),
    (1, -1),
    (0, -1),
    (-1, -1),
];

pub fn annotate(minefield: &[&str]) -> Vec<String> {
    let num_rows = minefield.len();
    let num_cols = if num_rows > 0 { minefield[0].len() } else { 0 };
    let minefield: Vec<&[u8]> = minefield.into_iter().map(|s| s.as_bytes()).collect();
    (0..num_rows)
        .map(|i| {
            (0..num_cols)
                .map(|j| {
                    if minefield[i][j] == b'*' {
                        '*'
                    } else {
                        let count = count(&minefield, num_rows, num_cols, i, j);
                        if count > 0 {
                            ('0' as u8 + count) as char
                        } else {
                            ' '
                        }
                    }
                })
                .collect()
        })
        .collect()
}

fn count(minefield: &Vec<&[u8]>, num_rows: usize, num_cols: usize, row: usize, col: usize) -> u8 {
    OFFSETS
        .into_iter()
        .filter(|(r, c)| {
            row as i32 + r >= 0
                && row as i32 + r < num_rows as i32
                && col as i32 + c >= 0
                && col as i32 + c < num_cols as i32
        })
        .fold(0, |acc, (r, c)| {
            if minefield[(row as i32 + r) as usize][(col as i32 + c) as usize] == b'*' {
                acc + 1
            } else {
                acc
            }
        })
}
