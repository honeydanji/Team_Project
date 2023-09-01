const stats = [
    { id: 1, name: 'The Number of classes that you have classified so far', value: '10' },
    { id: 2, name: 'You have run the system for', value: '52' },
    { id: 3, name: 'of results are recorded', value: '213' },
  ]
  
  export default function Stats() {
    return (
      <div className="bg-[#304386] pt-2 pb-5 sm:py-32">
        <div className="mx-auto max-w-7xl px-6 lg:px-8">
          <dl className="grid grid-cols-1 gap-x-8 gap-y-16 text-center lg:grid-cols-3">
            {stats.map((stat) => (
              <div key={stat.id} className="mx-auto flex max-w-xs flex-col gap-y-4">
                <dt className="text-xl leading-7 text-white">{stat.name}</dt>
                <dd className="order-first text-3xl font-semibold tracking-tight text-white sm:text-5xl">
                  {stat.value}
                </dd>
              </div>
            ))}
          </dl>
        </div>
      </div>
    )
  }
  