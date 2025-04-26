use std::collections::HashMap;

pub fn lowest_price(books: &[u32]) -> u32 {
    fn price(n: u32) -> u32 {
        let discount = match n {
            5 => 25,
            4 => 20,
            3 => 10,
            2 => 5,
            _ => 0,
        };
        n * (100 - discount) * 8
    }

    fn lowest_price(book_counts: [i32; 5], memo: &mut HashMap<[i32; 5], u32>) -> u32 {
        fn calculate_price(
            group: u32,
            depth: u32,
            indexes: &mut Vec<usize>,
            mut book_counts: [i32; 5],
            memo: &mut HashMap<[i32; 5], u32>,
            min_price: &mut u32,
        ) {
            if depth == 0 {
                for i in indexes.iter() {
                    book_counts[i - 1] -= 1;
                }
                let p = lowest_price(book_counts, memo);
                if p != u32::MAX {
                    *min_price = *min_price.min(&mut (p + price(group)));
                }
                return;
            }
            for i in *indexes.last().unwrap_or(&0)..5 {
                indexes.push(i + 1);
                calculate_price(group, depth - 1, indexes, book_counts, memo, min_price);
                indexes.pop();
            }
        }

        if book_counts.iter().any(|count| *count < 0) {
            return u32::MAX;
        }
        if let Some(price) = memo.get(&book_counts) {
            return *price;
        }
        let mut min_price = u32::MAX;
        calculate_price(1, 1, &mut vec![], book_counts, memo, &mut min_price);
        calculate_price(2, 2, &mut vec![], book_counts, memo, &mut min_price);
        calculate_price(3, 3, &mut vec![], book_counts, memo, &mut min_price);
        calculate_price(4, 4, &mut vec![], book_counts, memo, &mut min_price);
        calculate_price(5, 5, &mut vec![], book_counts, memo, &mut min_price);
        let min_price = if min_price == u32::MAX { 0 } else { min_price };
        memo.insert(book_counts.clone(), min_price);
        min_price
    }

    let mut book_counts = [0, 0, 0, 0, 0];
    let book_counts = books.into_iter().fold(&mut book_counts, |acc, n| {
        acc[*n as usize - 1] += 1;
        acc
    });
    lowest_price(*book_counts, &mut HashMap::new())
}
