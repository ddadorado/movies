//
//  MoviesViewModel.swift
//  moviesMp
//
//  Created by Training Sj on 10/26/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

class MoviesViewModel: ObservableObject {
    let movieClient = MovieClient()
    @Published var movies: [MovieData] = []

    init() {
        DispatchQueue.global().async {
            Task { @MainActor in
                do {
                    self.movies = try await self.movieClient.fetchMovies()
                    
                } catch {
                    print("Error fetching movies: \(error)")
                }
            }
        }
        
    }


    func toggleFavorite(for movie: MovieData) {
        if let index = movies.firstIndex(where: { $0.id == movie.id }) {
            self.movies[index].isFavorite.toggle()
            objectWillChange.send()
        }
    }

    func getFavoriteMovies() -> [MovieData] {
        return self.movies.filter { $0.isFavorite }
    }

    func fetchMovies() async throws -> [MovieData] {
        return try await movieClient.fetchMovies()
    }
}
