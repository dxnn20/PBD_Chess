import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {of} from 'rxjs'; // For handling fallback data on error
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {MatFormField} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatButton} from "@angular/material/button";
import {
  MatDatepicker,
  MatDatepickerInput,
  MatDatepickerModule,
  MatDatepickerToggle
} from "@angular/material/datepicker";
import {MatLabel} from "@angular/material/form-field";
import {MatNativeDateModule} from "@angular/material/core";

// Define interfaces for type safety

interface Player {
  id: number;
  name: string;
  dateOfBirth: string;
  registrationDate: string;
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
    MatButton, MatDatepickerToggle, MatDatepickerInput, MatDatepicker, MatLabel, MatDatepickerModule, MatNativeDateModule],
  providers: [MatDatepickerModule]
})

export class HomeComponent {
  constructor(private http: HttpClient) {
  }

  currentView: string = ''; // Track the active view
  newGame: Game = {

  }// Form data for creating a new game

  games: Game[] = [];
  bestPlayer: BestPlayer | null = null;
  mostGamesPlayer: PlayerWithMostGamesDTO | null = null;

  private apiUrl = 'http://localhost:8081';


  showCreateGameForm() {
    this.currentView = 'create-game';
  }

  fetchGamesByDate() {

    this.currentView = 'game-table';
    this.http
      .get<Game[]>(`${this.apiUrl}/games/get-by-date`, {
        params: {startDate: '2024-01-01', endDate: '2024-04-01'}
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



}
