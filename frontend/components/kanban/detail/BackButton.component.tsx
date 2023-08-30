"use client";
import { useRouter } from "next/navigation";
import { FC } from "react";

interface KanbanDetailBackButtonProps {
  generation: string;
}

const KanbanDetailBackButton: FC<KanbanDetailBackButtonProps> = ({
  generation,
}) => {
  const navigate = useRouter();

  const onBackClick = () => {
    navigate.push(`/kanban/${generation}`);
  };
  return (
    <button onClick={onBackClick} className="w-8 ">
      <img src="/icons/chevron.backward.svg" alt="" />
    </button>
  );
};

export default KanbanDetailBackButton;
