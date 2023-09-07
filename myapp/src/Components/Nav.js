import { Fragment } from 'react'
import { Popover, Transition } from '@headlessui/react'
import { faBars } from '@fortawesome/free-solid-svg-icons'

import {
  ArrowPathIcon,
  ChartPieIcon,
  CursorArrowRaysIcon,
  FingerPrintIcon,
  SquaresPlusIcon,
} from '@heroicons/react/24/outline'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

const solutions = [
  { name: 'FrontPage', href: '/', icon: ChartPieIcon },
  { name: 'DragDrop', href: '/dragdrop', icon: CursorArrowRaysIcon },
  { name: 'Service', href: '/service', icon: FingerPrintIcon },
  { name: 'History', href: '/history', icon: SquaresPlusIcon },
  { name: 'DataResults', href: '/dataresults', icon: ArrowPathIcon },
  { name: 'LogIn', href: '/login', icon: ArrowPathIcon },
]

export default function Nav() {
  return (
    <Popover className={`fixed inset-0 flex items-center justify-center`}>
      <Popover.Button className="inline-flex items-center gap-x-1 text-sm font-semibold leading-6 text-gray-900">
        <FontAwesomeIcon icon={faBars} style={{ color: '#888', position: 'absolute', left: '1850px', top: '30px', fontSize: '2rem'}} />        
      </Popover.Button>

      <Transition
        as={Fragment}
        enter="transition ease-out duration-200"
        enterFrom="opacity-0 translate-y-1"
        enterTo="opacity-100 translate-y-0"
        leave="transition ease-in duration-150"
        leaveFrom="opacity-100 translate-y-0"
        leaveTo="opacity-0 translate-y-1"
      >
        <Popover.Panel className="fixed right-5 top-20 transform  z-10 mt-1 ...">
          <div className="w-screen max-w-sm flex-auto overflow-hidden rounded-3xl bg-white text-sm leading-6 shadow-lg ring-1 ring-gray-900/5">
            <div className="p-4 h-auto">
              {solutions.map((item) => (
                <div key={item.name} className="group relative h-3 flex gap-x-4 rounded-lg p-4 hover:bg-gray-50">
                  <div className="mt-1 flex w-11 flex-none items-center justify-center rounded-lg bg-gray-50 group-hover:bg-white">
                    <item.icon className="h-6 w-6 text-gray-600 group-hover:text-indigo-600" aria-hidden="true" />
                  </div>
                  <div>
                    <a href={item.href} className="font-semibold text-gray-900">
                      {item.name}
                      <span className="absolute inset-0" />
                    </a>                    
                  </div>
                </div>
              ))}
            </div>
          </div>
        </Popover.Panel>
      </Transition>
    </Popover>
  )
}
