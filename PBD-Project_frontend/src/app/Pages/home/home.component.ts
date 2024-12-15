import {Component} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {Observable, of} from 'rxjs'; // For handling fallback data on error
import {CommonModule, formatDate, NgOptimizedImage} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {MatError, MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatButton} from "@angular/material/button";
import {
  MatDatepicker,
  MatDatepickerInput,
  MatDatepickerModule,
  MatDatepickerToggle
} from "@angular/material/datepicker";
import {MatLabel} from "@angular/material/form-field";
import {MatNativeDateModule, MatOption} from "@angular/material/core";
import {MatSelect} from "@angular/material/select";
import {
  MatCell, MatCellDef, MatColumnDef,
  MatHeaderCell, MatHeaderCellDef,
  MatHeaderRow,
  MatHeaderRowDef,
  MatRow,
  MatRowDef,
  MatTable
} from "@angular/material/table";
import {
  MatCard,
  MatCardTitle,
  MatCardSubtitle,
  MatCardActions,
  MatCardContent,
  MatCardHeader,
  MatCardImage
} from "@angular/material/card";
import {DirectTemplateSourceMapping} from "@angular/compiler-cli/src/ngtsc/typecheck/api";

// Define interfaces for type safety

interface Player {
  id: number;
  name: string;
  birth_date: string;
  registration_date: string;
}

interface Game {
  id?: number;              // Unique identifier for the game
  startDate?: string;       // Start date of the game
  endDate?: string;         // End date of the game
  type?: string;            // Type of the game
  winner?: Player;          // Name of the winning player
  nrPartide?: number;       // Number of matches in the game
  jucator1?: Player;        // Details of Player 1
  jucator2?: Player;        // Details of Player 2
  nrPartideJucate?: number; // Total games played
  scorJucator1?: number;    // Score of Player 1
  scorJucator2?: number;    // Score of Player 2
}

interface BestPlayer {
  player: Player;
  winCount: number;
}

interface PlayerWithMostGamesDTO {
  player: Player;
  nrOfWins: number;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule, MatFormField, MatInput, MatFormField, MatInput,
    MatButton, MatDatepickerToggle, MatDatepickerInput, MatCardTitle, MatCardSubtitle, MatDatepicker, MatLabel, MatDatepickerModule, MatNativeDateModule, MatError, MatSelect, MatOption, MatTable, MatHeaderCell, MatCell, MatHeaderRow, MatRow, MatRowDef, MatHeaderRowDef, MatColumnDef, MatHeaderCellDef, MatCellDef, MatCard, MatCardHeader, MatCardContent, MatCardActions, MatCardImage, NgOptimizedImage],
  providers: [MatDatepickerModule]
})

export class HomeComponent {
  startDateForGames: string | null = null;
  endDateForGames: string | null = null;
  playerId: number  = 0;

  constructor(private http: HttpClient) {
  }

  gameTypes: string[] = ['Chess', 'Checkers']; // Dropdown options
  displayedColumns: string[] = ['id', 'type', 'startDate', 'endDate', 'player1', 'player2', 'winner', 'nrPartide', 'scorJucator1', 'scorJucator2', 'nrPartideJucate']; // Table columns

  currentView: string = ''; // Track the active view
  newGame: Game = {}// Form data for creating a new game

  games: Game[] = [];
  bestPlayer: BestPlayer | null = null;
  mostGamesPlayer: PlayerWithMostGamesDTO | null = null;
  minAge: number = 0;
  maxAge: number = 0;

  private apiUrl = 'http://localhost:8081';

  showPlayerGames() {
    this.currentView = 'player-games';
  }

  showCreateGameForm() {
    this.currentView = 'create-game';
  }

  fetchGames() {
    this.currentView = 'game-table';
  }

  fetchGamesByDate() {
    if (!this.startDateForGames || !this.endDateForGames) {
      alert('Please select both start and end dates.');
      return;
    }

    this.currentView = 'game-table';
    this.http
      .get<Game[]>(`${this.apiUrl}/games/get-by-date`, {
        params: {
          startDate: this.startDateForGames,
          endDate: this.endDateForGames
        }
      })
      .pipe(
        tap((data) => {
          console.log('Received games:', data);
          this.games = data;
        }),
        catchError((error) => {
          console.error('Error fetching games:', error);
          return of([]);
        })
      )
      .subscribe();
  }

  fetchBestPlayer() {
    this.currentView = 'best-player';
    this.http
      .get<BestPlayer>(`${this.apiUrl}/players/best-player`)
      .pipe(
        tap((data) => {
          console.log('Received best player:', data);
          this.bestPlayer = data;
        }),
        catchError((error) => {
          console.error('Error fetching best player:', error);
          return of(null);
        })
      )
      .subscribe();
  }

  fetchMostGamesPlayer() {
    this.currentView = 'most-games-player';
    this.http
      .get<PlayerWithMostGamesDTO>(`${this.apiUrl}/players/most-games-player`)
      .pipe(
        tap((data) => {
          console.log('Received player with most games:', data);
          this.mostGamesPlayer = data;
        }),
        catchError((error) => {
          console.error('Error fetching player with most games:', error);
          return of(null);
        })
      )
      .subscribe();
  }

  submitNewGame() {
    const locale = 'en-US'; // Ensure locale is set for consistent formatting

    // Format the dates to 'yyyy-MM-dd'
    if (this.newGame.startDate) {
      this.newGame.startDate = formatDate(this.newGame.startDate, 'yyyy-MM-dd', locale);
    }

    if (this.newGame.endDate) {
      this.newGame.endDate = formatDate(this.newGame.endDate, 'yyyy-MM-dd', locale);
    }

    console.log('Creating game:', this.newGame);

    this.http
      .post<Game>(`${this.apiUrl}/games/create`, this.newGame)
      .pipe(
        tap((createdGame) => {
          console.log('Created game:', createdGame);
          alert('Game created successfully!');
          this.newGame = {};
        }),
        catchError((error) => {
          console.error('Error creating game:', error);
          alert('Failed to create the game. Please try again.');
          return of(null);
        })
      )
      .subscribe();
  }

  fetchGamesByPlayer(playerId: number): void {
    if (!playerId) {
      console.error('Player ID is required');
      return;
    }

    this.http
      .get<Game[]>(`${this.apiUrl}/players/get-games-by-player-id`, { params: { id: playerId.toString() } })
      .pipe(
        tap((games) => {
          console.log('Received games by player:', games);
          this.games = games;
          this.currentView = 'game-table';
        }),
        catchError((error) => {
          console.error('Error fetching games by player', error);
          return of([]); // Return empty array on error
        })
      )
      .subscribe();
  }

  updateGame(game: Game) {

    this.http
      .put<Game>(`${this.apiUrl}/games/update`, game)
      .pipe(
        tap((updatedGame) => {
          console.log('Updated game:', updatedGame);
          alert('Game updated successfully!');
          this.fetchGames(); // Fetch updated game list
        }),
        catchError((error) => {
          console.error('Error updating game:', error);
          alert('Failed to update the game. Please try again.');
          return of(null);
        })
      )
      .subscribe();
  }

  getBestPlayerByAgeView() {
    this.bestPlayer = null;
    this.currentView = 'best-player-by-age';
  }

  getBestPlayerByAgeInterval(): void {

    console.log('Fetching best player by age interval:', this.minAge, this.maxAge);

    this.http.get<BestPlayer>(`${this.apiUrl}/games/get-best-player-by-age-interval`, {
      params: {
        minAge: this.minAge,
        maxAge: this.maxAge
      }
    }).subscribe((data) => {
        this.bestPlayer = data;
        console.log('Received best player by age interval:', this.bestPlayer);
      }
    )
  }

}
