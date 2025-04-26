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

    fn lowest_price<'a>(
        book_counts: &mut [i32; 5],
        memo: &mut HashMap<[i32; 5], u32>,
    ) -> Option<u32> {
        if book_counts.iter().any(|count| *count < 0) {
            return None;
        }
        if let Some(price) = memo.get(book_counts) {
            return Some(*price);
        }
        let mut answer = u32::MAX;
        for i in 0..5 {
            book_counts[i] -= 1;
            if let Some(p) = lowest_price(book_counts, memo) {
                answer = answer.min(p + price(1));
            }
            book_counts[i] += 1;
        }
        for i in 0..5 {
            for j in i + 1..5 {
                book_counts[i] -= 1;
                book_counts[j] -= 1;
                if let Some(p) = lowest_price(book_counts, memo) {
                    answer = answer.min(p + price(2));
                }
                book_counts[i] += 1;
                book_counts[j] += 1;
            }
        }
        for i in 0..5 {
            for j in i + 1..5 {
                for k in j + 1..5 {
                    book_counts[i] -= 1;
                    book_counts[j] -= 1;
                    book_counts[k] -= 1;
                    if let Some(p) = lowest_price(book_counts, memo) {
                        answer = answer.min(p + price(3));
                    }
                    book_counts[i] += 1;
                    book_counts[j] += 1;
                    book_counts[k] += 1;
                }
            }
        }
        for i in 0..5 {
            for j in i + 1..5 {
                for k in j + 1..5 {
                    for l in k + 1..5 {
                        book_counts[i] -= 1;
                        book_counts[j] -= 1;
                        book_counts[k] -= 1;
                        book_counts[l] -= 1;
                        if let Some(p) = lowest_price(book_counts, memo) {
                            answer = answer.min(p + price(4));
                        }
                        book_counts[i] += 1;
                        book_counts[j] += 1;
                        book_counts[k] += 1;
                        book_counts[l] += 1;
                    }
                }
            }
        }
        for i in 0..5 {
            for j in i + 1..5 {
                for k in j + 1..5 {
                    for l in k + 1..5 {
                        for m in l + 1..5 {
                            book_counts[i] -= 1;
                            book_counts[j] -= 1;
                            book_counts[k] -= 1;
                            book_counts[l] -= 1;
                            book_counts[m] -= 1;
                            if let Some(p) = lowest_price(book_counts, memo) {
                                answer = answer.min(p + price(5));
                            }
                            book_counts[i] += 1;
                            book_counts[j] += 1;
                            book_counts[k] += 1;
                            book_counts[l] += 1;
                            book_counts[m] += 1;
                        }
                    }
                }
            }
        }
        let answer = if answer == u32::MAX { 0 } else { answer };
        memo.insert(book_counts.clone(), answer);
        Some(answer)
    }

    let mut book_counts = [0, 0, 0, 0, 0];
    let mut book_counts = books.into_iter().fold(&mut book_counts, |acc, n| {
        acc[*n as usize - 1] += 1;
        acc
    });
    lowest_price(&mut book_counts, &mut HashMap::new()).unwrap_or(0)
}
