import { Fragment, useRef, useState } from 'react'
import { Dialog, Transition } from '@headlessui/react'
import jwtDecode from 'jwt-decode'

export default function Modals({ selectedItemData, selectedItem }) {

  const [open, setOpen] = useState(true)
  const cancelButtonRef = useRef(null)

  const extractSelectedItemData = selectedItemData ? Object.values(selectedItemData) : [];
  console.log("extractSelectedItemData: ", extractSelectedItemData)

  const token = localStorage.getItem('token');
  const splitToken = token.split(" ")[1];
  const decodedToken = jwtDecode(splitToken);
  const extractedName = decodedToken.name;

  return (
    <Transition.Root show={open} as={Fragment}>
      <Dialog as="div" className="relative z-10" initialFocus={cancelButtonRef} onClose={setOpen}>
        <Transition.Child
          as={Fragment}
          enter="ease-out duration-300"
          enterFrom="opacity-0"
          enterTo="opacity-100"
          leave="ease-in duration-200"
          leaveFrom="opacity-100"
          leaveTo="opacity-0"
        >
          <div className="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" />
        </Transition.Child>

        <div className="fixed inset-0 z-10 overflow-y-auto flex items-center justify-center">
          <div className="max-h-full w-10 p-4 text-center sm:p-0">
            <Transition.Child
              as={Fragment}
              enter="ease-out duration-300"
              enterFrom="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
              enterTo="opacity-100 translate-y-0 sm:scale-100"
              leave="ease-in duration-200"
              leaveFrom="opacity-100 translate-y-0 sm:scale-100"
              leaveTo="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
            >
              <Dialog.Panel className="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all" style={{ height: '30vw', width: '80vw', left: '50%',  transform: 'translateX(-50%)', }}>
                <div className="bg-white px-4 pb-4 pt-5 sm:p-6 sm:pb-4">
                  <button
                    type="button"
                    className="absolute top-5 right-5 rounded-md px-3 py-2 text-sm font-semibold text-black shadow-sm hover:bg-blue-500 sm:ml-3 sm:h-10 sm:w-auto"
                    onClick={() => setOpen(false)}
                  >
                    X
                  </button>
                  <div className="flex items-center justify-center">
                    <div className="mt-3 text-center sm:ml-4 sm:mt-0 sm:text-left">
                      <Dialog.Title as="h3" className="text-1xl font-semibold leading-10 text-gray-900">
                        Details of your history
                      </Dialog.Title>
                      <div className='text-center'>
                        <p className='font-semibold text-gray-500'>{selectedItem}</p>
                      </div>
                    </div>
                  </div>
                </div>
                <div className="bg-gray-50 px-5 py-3 sm:flex sm:flex-row sm:px-6">
                  <table>
                    <thead>
                      <tr>
                        <th className="px-11 py-2">Timestamp</th>
                        <th className="px-11 py-2">User</th>
                        <th className="px-11 py-2">원본이미지(input)</th>
                        <th className="px-11 py-2">처리이미지(output)</th>
                        <th className="px-11 py-2">Comment</th>
                      </tr>
                    </thead>
                    <tbody>
                      {extractSelectedItemData.map((item, index) => (
                        <tr key={index}>
                          <td className="px-11 py-2">{item.uploadTime}</td>
                          <td className="px-11 py-2">{extractedName}</td>
                          <td className="px-11 py-2">{item.twoOriginalPath}</td>
                          <td className="px-11 py-2">{item.twoSegmentationPath}</td>
                          <td className="px-11 py-2">{item.comment}</td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              </Dialog.Panel>
            </Transition.Child>
          </div>
        </div>
      </Dialog>
    </Transition.Root>
  )
}
