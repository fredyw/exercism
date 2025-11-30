pub fn spiral_matrix(size: u32) -> Vec<Vec<u32>> {
    let mut matrix = vec![vec![0; size as usize]; size as usize];
    let mut n = 1;
    let mut row: isize = 0;
    let mut col: isize = 0;
    let mut lo_row: isize = 0;
    let mut hi_row: isize = size as isize - 1;
    let mut lo_col: isize = 0;
    let mut hi_col: isize = size as isize - 1;
    while n <= size * size {
        right(&mut matrix, &mut n, row, hi_col, row, &mut col);
        row += 1;
        col -= 1;

        down(&mut matrix, &mut n, row, hi_row, &mut row, col);
        row -= 1;
        col -= 1;

        left(&mut matrix, &mut n, lo_col, col, row, &mut col);
        row -= 1;
        col += 1;

        up(&mut matrix, &mut n, lo_row + 1, row, &mut row, col);
        row += 1;
        col += 1;

        lo_row += 1;
        hi_row -= 1;
        lo_col += 1;
        hi_col -= 1;
    }
    matrix
}

fn right(
    matrix: &mut Vec<Vec<u32>>,
    n: &mut u32,
    from: isize,
    to: isize,
    row: isize,
    col: &mut isize,
) {
    for _ in from..=to {
        matrix[row as usize][*col as usize] = *n;
        *col += 1;
        *n += 1;
    }
}

fn down(
    matrix: &mut Vec<Vec<u32>>,
    n: &mut u32,
    from: isize,
    to: isize,
    row: &mut isize,
    col: isize,
) {
    for _ in from..=to {
        matrix[*row as usize][col as usize] = *n;
        *row += 1;
        *n += 1;
    }
}

fn left(
    matrix: &mut Vec<Vec<u32>>,
    n: &mut u32,
    from: isize,
    to: isize,
    row: isize,
    col: &mut isize,
) {
    for _ in (from..=to).rev() {
        matrix[row as usize][*col as usize] = *n;
        *col -= 1;
        *n += 1;
    }
}

fn up(
    matrix: &mut Vec<Vec<u32>>,
    n: &mut u32,
    from: isize,
    to: isize,
    row: &mut isize,
    col: isize,
) {
    for _ in (from..=to).rev() {
        matrix[*row as usize][col as usize] = *n;
        *row -= 1;
        *n += 1;
    }
}
