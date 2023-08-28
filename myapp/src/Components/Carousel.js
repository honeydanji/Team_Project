import React, { useState, useEffect, useRef } from 'react';
import './Carousel.css'

export default function Carousel({ carouselList }) {
  const [currIndex, setCurrIndex] = useState(1);
  const [currList, setCurrList] = useState();

  const carouselRef = useRef(null);

  useEffect(() => {
    if (carouselList.length !== 0) {
      const startData = carouselList[0];
      const endData = carouselList[carouselList.length - 1];
      const newList = [endData, ...carouselList, startData];

      setCurrList(newList);
    }
  }, [carouselList]);

  useEffect(() => {
    if (carouselRef.current !== null) {
      carouselRef.current.style.transform = `translateX(-${currIndex}00%)`;
    }
  }, [currIndex]);

  const moveToNthSlide = (index) => {
    setTimeout(() => {
      setCurrIndex(index);
      if (carouselRef.current !== null) {
        carouselRef.current.style.transition = '';
      }
    }, 500);
  };

  const handleSwipe = (direction) => {
    const newIndex = currIndex + direction;

    if (newIndex === carouselList.length + 1) {
      moveToNthSlide(1);
    } else if (newIndex === 0) {
      moveToNthSlide(carouselList.length);
    }

    setCurrIndex((prev) => prev + direction);
    if (carouselRef.current !== null) {
      carouselRef.current.style.transition = 'all 0.5s ease-in-out';
    }
  };

  let touchStartX;
  let touchEndX;

  const handleTouchStart = (e) => {
    touchStartX = e.nativeEvent.touches[0].clientX;
  };

  const handleTouchMove = (e) => {
    const currTouchX = e.nativeEvent.changedTouches[0].clientX;

    if (carouselRef.current !== null) {
      carouselRef.current.style.transform = `translateX(calc(-${currIndex}00% - ${
        (touchStartX - currTouchX) * 2 || 0
      }px))`;
    }
  };

  const handleTouchEnd = (e) => {
    touchEndX = e.nativeEvent.changedTouches[0].clientX;

    if (touchStartX >= touchEndX) {
      handleSwipe(1);
    } else {
      handleSwipe(-1);
    }
  };

  // 자동 슬라이드 인터벌
  useEffect(() => {
    const autoSlideInterval = setInterval(() => {
        handleSwipe(1); // 다음 슬라이드로 이동        
    }, 5000) // 5초마다 슬라이드 변경

    return () => {
        clearInterval(autoSlideInterval); // 컴포넌트 언마운트 시 인터벌 해제
    };
  }, [currIndex]);

  return (
    <div className="container">
      <div
        className="carouselWrapper"
        onTouchStart={handleTouchStart}
        onTouchMove={handleTouchMove}
        onTouchEnd={handleTouchEnd}
      >
        <button type='button' className="swipeLeft" onClick={() => handleSwipe(-1)}>
          Previous
        </button>
        <button type='button' className="swipeRight" onClick={() => handleSwipe(1)}>
          Next
        </button>
        <ul className="carousel" ref={carouselRef}>
          {currList?.map((image, idx) => {
            const key = `${image}-${idx}`;

            return (
              <li key={key} className="carouselItem">
                <img src={image} alt='carousel-img' />
              </li>
            );
          })}
        </ul>
      </div>
    </div>
  );
}
