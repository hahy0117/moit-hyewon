import requests
from django.http import JsonResponse
from django.shortcuts import render


SPRING_API_URL = "http://localhost:8080/api/reviews/statistics"


def get_statistics():
    response = requests.get(SPRING_API_URL, timeout=5)
    response.raise_for_status()

    reviews = response.json()

    ratings = [
        review["rating"]
        for review in reviews
        if review["rating"] is not None
    ]

    total_reviews = len(reviews)

    average_rating = round(
        sum(ratings) / len(ratings), 2
    ) if ratings else 0

    rating_distribution = {
        "1": ratings.count(1),
        "2": ratings.count(2),
        "3": ratings.count(3),
        "4": ratings.count(4),
        "5": ratings.count(5),
    }

    total_likes = sum(
        review["likesCount"] or 0
        for review in reviews
    )

    total_views = sum(
        review["viewsCount"] or 0
        for review in reviews
    )

    public_reviews = sum(
        1 for review in reviews
        if review["isPublic"] == "Y"
    )

    private_reviews = sum(
        1 for review in reviews
        if review["isPublic"] == "N"
    )

    return {
        "totalReviews": total_reviews,
        "averageRating": average_rating,
        "ratingDistribution": rating_distribution,
        "totalLikes": total_likes,
        "totalViews": total_views,
        "publicReviews": public_reviews,
        "privateReviews": private_reviews,
    }


def statistics(request):
    try:
        data = get_statistics()

        return JsonResponse(data)

    except requests.RequestException as e:
        return JsonResponse({
            "error": "Spring Boot API 연결 실패",
            "message": str(e)
        }, status=500)


def dashboard(request):
    try:
        data = get_statistics()

        return render(
            request,
            "reviews/dashboard.html",
            {"data": data}
        )

    except requests.RequestException as e:
        return render(
            request,
            "reviews/dashboard.html",
            {
                "data": None,
                "error": str(e)
            }
        )