import { Fragment, useEffect, useState } from 'react'
import { Listbox, Transition } from '@headlessui/react'
import { CheckIcon, ChevronUpDownIcon } from '@heroicons/react/20/solid'
import { useNavigate } from 'react-router-dom';

function classNames(...classes) {
  return classes.filter(Boolean).join(' ')
}

export default function SelectDate({ dateList }) {
  const receivedDateList = dateList ? [...new Set(Object.values(dateList))] : []; 
  const [selected, setSelected] = useState(receivedDateList[0]);
  const [selectedDate, setSelectedDate] = useState('');
  const [requestedData, setRequestedDate] = useState([]);
  const navigate = useNavigate();

  const handleDateClick = (date) => {
    setSelectedDate(date);
    console.log("selectedDate: ", selectedDate);

    if(selectedDate) {
      const serverURL = `http://10.125.121.183:8080/results/${selectedDate}`;
      const token = localStorage.getItem('token');

      fetch(serverURL, {
        method: 'GET',
        headers: {
          'Content-Type' : 'application/json',
          'authorization' : `${token}`,
        },
      })
      .then((response) => {
        if(!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then((data) => {
        console.log("서버응답데이터: ", data);
        setRequestedDate(data);
        navigate("/dataresults", { state: { requestedData } });
      })
      .catch((error) => {
        console.error('오류발생: ', error);
      });
    }
  }
  
  // useEffect(() => {

  //   if(selectedDate && requestedData.length === 0) {
  //     const serverURL = `http://10.125.121.183:8080/results/${selectedDate}`;
  //     const token = localStorage.getItem('token');

  //     fetch(serverURL, {
  //       method: 'GET',
  //       headers: {
  //         'Content-Type' : 'application/json',
  //         'authorization' : `${token}`,
  //       },
  //     })
  //     .then((response) => {
  //       if(!response.ok) {
  //         throw new Error('Network response was not ok');
  //       }
  //       return response.json();
  //     })
  //     .then((data) => {
  //       console.log("서버응답데이터: ", data);
  //       setRequestedDate(data);
  //       navigate("/dataresults", { state: { requestedData } });
  //     })
  //     .catch((error) => {
  //       console.error('오류발생: ', error);
  //     });
  //   }
  
  // }, [navigate, requestedData, selectedDate])

  return (
    <Listbox value={selected} onChange={setSelected}>
      {({ open }) => (
        <>
          <Listbox.Label className="block text-lg font-medium leading-6 text-white">Choose the Date</Listbox.Label>
          <div className="relative mt-2">
            <Listbox.Button className="relative w-full cursor-default rounded-md bg-white py-3 pl-3 pr-10 text-left text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:outline-none focus:ring-2 focus:ring-indigo-500 sm:text-lg sm:leading-6">
              <span className="flex items-center">                
                <span className="ml-3 block truncate">{selected}</span>
              </span>
              <span className="pointer-events-none absolute inset-y-0 right-0 ml-3 flex items-center pr-2">
                <ChevronUpDownIcon className="h-5 w-5 text-gray-400" aria-hidden="true" />
              </span>
            </Listbox.Button>

            <Transition
              show={open}
              as={Fragment}
              leave="transition ease-in duration-100"
              leaveFrom="opacity-100"
              leaveTo="opacity-0"
            >
              <Listbox.Options className="absolute z-10 mt-1 max-h-56 w-full overflow-auto rounded-md bg-white py-1 pl-2 text-base shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none sm:text-lg">
                {receivedDateList.map((date, index) => (
                  <Listbox.Option
                    key={index}
                    onClick={() => handleDateClick(date)}
                    className={({ active }) =>
                      classNames(
                        active ? 'bg-indigo-600 text-white' : 'text-gray-900',
                        'relative cursor-default select-none py-2'
                      )                      
                    }
                    value={date}
                  >
                    {({ selected, active }) => (
                      <>
                        <div className="flex items-center">                          
                          <span
                            className={classNames(selected ? 'font-semibold' : 'font-normal', 'ml-3 block truncate')}
                          >
                            {date}
                          </span>
                        </div>

                        {selected ? (
                          <span
                            className={classNames(
                              active ? 'text-white' : 'text-indigo-600',
                              'absolute inset-y-0 right-0 flex items-center pr-3'
                            )}
                          >
                            <CheckIcon className="h-5 w-5" aria-hidden="true" />
                          </span>
                        ) : null}
                      </>
                    )}
                  </Listbox.Option>
                ))}
              </Listbox.Options>
            </Transition>
          </div>
        </>
      )}
    </Listbox>
  )
}
