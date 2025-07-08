// sample movie data for testing
export const sampleMovies = [
  {
    id: 1,
    title: "The Dark Knight",
    year: 2008,
    type: "movie",
    rating: 9.0,
    genre: "Action, Crime, Drama",
    duration: "152 min",
    poster: "https://images.unsplash.com/photo-1440404653325-ab127d49abc1?w=300&h=450&fit=crop",
    description: "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
    director: "Christopher Nolan",
    cast: ["Christian Bale", "Heath Ledger", "Aaron Eckhart"],
    reviews: [
      { id: 1, user: "MovieBuff2024", rating: 5, comment: "Absolutely phenomenal! Heath Ledger's performance is unforgettable.", date: "2024-01-15" },
      { id: 2, user: "CinemaLover", rating: 5, comment: "A masterpiece of filmmaking. Every scene is perfectly crafted.", date: "2024-01-10" }
    ]
  },
  {
    id: 2,
    title: "Breaking Bad",
    year: 2008,
    type: "series",
    rating: 9.5,
    genre: "Crime, Drama, Thriller",
    duration: "5 seasons",
    poster: "https://images.unsplash.com/photo-1489599735504-21e8f9c4a459?w=300&h=450&fit=crop",
    description: "A high school chemistry teacher diagnosed with inoperable lung cancer turns to manufacturing and selling methamphetamine in order to secure his family's future.",
    director: "Vince Gilligan",
    cast: ["Bryan Cranston", "Aaron Paul", "Anna Gunn"],
    reviews: [
      { id: 1, user: "SeriesAddict", rating: 5, comment: "The greatest TV series ever made. Character development is incredible.", date: "2024-01-20" },
      { id: 2, user: "TVCritic", rating: 5, comment: "Brilliant writing and acting. A true work of art.", date: "2024-01-18" }
    ]
  },
  {
    id: 3,
    title: "Inception",
    year: 2010,
    type: "movie",
    rating: 8.8,
    genre: "Action, Sci-Fi, Thriller",
    duration: "148 min",
    poster: "https://images.unsplash.com/photo-1489599735504-21e8f9c4a459?w=300&h=450&fit=crop",
    description: "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
    director: "Christopher Nolan",
    cast: ["Leonardo DiCaprio", "Marion Cotillard", "Tom Hardy"],
    reviews: [
      { id: 1, user: "SciFiFan", rating: 4, comment: "Mind-bending and visually stunning. Nolan at his best.", date: "2024-01-12" }
    ]
  },
  {
    id: 4,
    title: "Stranger Things",
    year: 2016,
    type: "series",
    rating: 8.7,
    genre: "Drama, Fantasy, Horror",
    duration: "4 seasons",
    poster: "https://images.unsplash.com/photo-1478720568477-b0ac8a9a6b99?w=300&h=450&fit=crop",
    description: "When a young boy disappears, his mother, a police chief and his friends must confront terrifying supernatural forces in order to get him back.",
    director: "The Duffer Brothers",
    cast: ["Millie Bobby Brown", "Finn Wolfhard", "Winona Ryder"],
    reviews: [
      { id: 1, user: "80sNostalgia", rating: 4, comment: "Perfect blend of horror and nostalgia. Great character development.", date: "2024-01-08" }
    ]
  }
];