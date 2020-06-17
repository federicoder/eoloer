import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { AssegnazioneTaskUpdateComponent } from 'app/entities/assegnazione-task/assegnazione-task-update.component';
import { AssegnazioneTaskService } from 'app/entities/assegnazione-task/assegnazione-task.service';
import { AssegnazioneTask } from 'app/shared/model/assegnazione-task.model';

describe('Component Tests', () => {
  describe('AssegnazioneTask Management Update Component', () => {
    let comp: AssegnazioneTaskUpdateComponent;
    let fixture: ComponentFixture<AssegnazioneTaskUpdateComponent>;
    let service: AssegnazioneTaskService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [AssegnazioneTaskUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AssegnazioneTaskUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AssegnazioneTaskUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AssegnazioneTaskService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AssegnazioneTask(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new AssegnazioneTask();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
