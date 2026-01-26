use std::cmp::Ordering;
use std::collections::HashMap;

#[derive(Debug, Default)]
struct Team {
    name: String,
    matches: u32,
    wins: u32,
    draws: u32,
    losses: u32,
    points: u32,
}

impl Team {
    fn new(name: String) -> Team {
        Team {
            name,
            ..Default::default()
        }
    }

    fn win(&mut self) {
        self.matches += 1;
        self.wins += 1;
        self.points += 3;
    }

    fn draw(&mut self) {
        self.matches += 1;
        self.draws += 1;
        self.points += 1;
    }

    fn lose(&mut self) {
        self.matches += 1;
        self.losses += 1;
    }
}

pub fn tally(match_results: &str) -> String {
    let mut teams: HashMap<String, Team> = HashMap::new();
    for line in match_results.split('\n') {
        if line.is_empty() {
            continue;
        }
        let parts: Vec<&str> = line.split(';').collect();
        let team1 = teams
            .entry(parts[0].to_string())
            .or_insert(Team::new(parts[0].to_string()));
        if parts[2] == "win" {
            team1.win();
        } else if parts[2] == "draw" {
            team1.draw();
        } else {
            team1.lose();
        }
        let team2 = teams
            .entry(parts[1].to_string())
            .or_insert(Team::new(parts[1].to_string()));
        if parts[2] == "win" {
            team2.lose();
        } else if parts[2] == "draw" {
            team2.draw();
        } else {
            team2.win();
        }
    }
    let mut sorted_teams = teams.values().collect::<Vec<&Team>>();
    sorted_teams.sort_by(|team1, team2| {
        let cmp = team2.points.cmp(&team1.points);
        match cmp {
            Ordering::Equal => team1.name.cmp(&team2.name),
            _ => cmp,
        }
    });
    let header = format!(
        "{:<30} | {:>2} | {:>2} | {:>2} | {:>2} | {:>2}",
        "Team", "MP", "W", "D", "L", "P"
    );
    let body = sorted_teams
        .iter()
        .map(|team| {
            format!(
                "{:<30} | {:>2} | {:>2} | {:>2} | {:>2} | {:>2}",
                team.name, team.matches, team.wins, team.draws, team.losses, team.points
            )
        })
        .collect::<Vec<_>>()
        .join("\n");
    format!("{}\n{}", header, body).trim().to_owned()
}
