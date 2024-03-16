use std::collections::HashMap;

#[derive(Debug)]
pub struct Item {
    pub weight: u32,
    pub value: u32,
}

pub fn maximum_value(max_weight: u32, items: &[Item]) -> u32 {
    fn maximum_value(
        items: &[Item],
        weight: i32,
        index: usize,
        memo: &mut HashMap<(usize, i32), u32>,
    ) -> u32 {
        if items.len() == index {
            return 0;
        }
        if let Some(value) = memo.get(&(index, weight)) {
            return *value;
        }
        let take = if weight - items[index].weight as i32 >= 0 {
            maximum_value(items, weight - items[index].weight as i32, index + 1, memo)
                + items[index].value
        } else {
            0
        };
        let skip = maximum_value(items, weight, index + 1, memo);
        let max = take.max(skip);
        memo.insert((index, weight), max);
        max
    }

    let mut memo = HashMap::new();
    maximum_value(items, max_weight as i32, 0, &mut memo)
}
