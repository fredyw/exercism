pub fn answer(command: &str) -> Option<i32> {
    let words: Vec<&str> = command[0..command.len() - 1].split(" ").collect();
    if words.len() < 3 || words[0] != "What" || words[1] != "is" {
        return None;
    }
    let mut answer = if let Ok(n) = words[2].parse::<i32>() {
        n
    } else {
        return None;
    };
    let mut i = 3;
    while i < words.len() {
        match words[i] {
            "plus" => {
                if i + 1 >= words.len() {
                    return None;
                }
                if let Ok(n) = words[i + 1].parse::<i32>() {
                    answer += n;
                } else {
                    return None;
                }
                i += 2;
            }
            "minus" => {
                if i + 1 >= words.len() {
                    return None;
                }
                if let Ok(n) = words[i + 1].parse::<i32>() {
                    answer -= n;
                } else {
                    return None;
                }
                i += 2;
            }
            "multiplied" => {
                if i + 2 >= words.len() || words[i + 1] != "by" {
                    return None;
                }
                if let Ok(n) = words[i + 2].parse::<i32>() {
                    answer *= n;
                } else {
                    return None;
                }
                i += 3;
            }
            "divided" => {
                if i + 2 >= words.len() || words[i + 1] != "by" {
                    return None;
                }
                if let Ok(n) = words[i + 2].parse::<i32>() {
                    answer /= n;
                } else {
                    return None;
                }
                i += 3;
            }
            "raised" => {
                if i + 4 >= words.len()
                    || words[i + 1] != "to"
                    || words[i + 2] != "the"
                    || words[i + 4] != "power"
                {
                    return None;
                }
                if let Ok(n) = &words[i + 3][0..words[i + 3].len() - 2].parse::<i32>() {
                    println!("n ==> {n}");
                    answer = answer.pow(*n as u32);
                }
                i += 5;
            }
            _ => return None,
        }
    }
    Some(answer)
}
