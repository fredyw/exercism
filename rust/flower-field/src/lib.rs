pub fn annotate(garden: &[&str]) -> Vec<String> {
    let num_rows = garden.len();
    let num_columns = if num_rows > 0 { garden[0].len() } else { 0 };
    let mut annotated = vec![" ".to_string().repeat(num_columns); num_rows];
    for row in 0..garden.len() {
        let b = garden[row].as_bytes();
        for col in 0..b.len() {
            unsafe {
                let bytes = annotated[row].as_bytes_mut();
                let count = count_flower(garden, row, col);
                if count > 0 {
                    bytes[col] = count + b'0';
                } else {
                    bytes[col] = b[col];
                }
            }
        }
    }
    annotated
}

fn count_flower(garden: &[&str], row: usize, col: usize) -> u8 {
    if garden[row].as_bytes()[col] == b'*' {
        return 0;
    }
    let num_rows = garden.len();
    let num_columns = if num_rows > 0 { garden[0].len() } else { 0 };
    let mut count = 0;
    for i in -1..=1 {
        for j in -1..=1 {
            if i == 0 && j == 0 {
                continue;
            }
            let row = row as isize + i;
            let col = col as isize + j;
            if row < 0 || row == num_rows as isize || col < 0 || col == num_columns as isize {
                continue;
            }
            if garden[row as usize].as_bytes()[col as usize] == b'*' {
                count += 1;
            }
        }
    }
    count
}
